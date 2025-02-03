package com.MU.TODO.service;

import com.MU.TODO.config.Neo4jConfig;
import com.MU.TODO.models.Todo;
import com.MU.TODO.models.User;
import com.MU.TODO.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;


@Service
@RequiredArgsConstructor
public class TodoService implements TodoRepo {

    private final Driver driver = new Neo4jConfig().neo4jDriver();

    //CREATE: create a t*do for a user
    @Override
    public Todo createTodo(String username, Long id, Todo todo) {
        try (Session session = driver.session()) {
            return session.executeWrite(tx -> {
                //1. Find or create the user
                String userQuery = "MERGE (u:User {username:$username, id:$id}) RETURN u.id AS userId";
                Long userId = tx.run(userQuery, parameters("username", username, "id", id))
                        .single()
                        .get("userId")
                        .asLong();

                //2. Create a t*do and connect it to the user
                String todoQuery = """
                         CREATE (t:Todo {id:$todoId,description:$description,completed:$completed})
                         WITH t
                         MATCH (u:User) WHERE u.id=$userId
                         CREATE (u)-[:OWNS]->(t)
                         RETURN t.id AS todoId,t.description AS description, t.completed AS completed
                        """;

                Record record = tx.run(todoQuery, parameters("todoId", todo.getId(),
                        "description", todo.getDescription(),
                        "completed", todo.isCompleted(),
                        "userId", id)).single();

                //3. Map the result to a t*do object
                todo.setId(record.get("todoId").asLong());
                return todo;
            });
        }
    }

    //READ: Get all Todos for a User
    @Override
    public List<Todo> getTodosByUser(String username) {

        try (Session session = driver.session()) {
            return session.executeRead(tx -> {

                String query = """
                        MATCH (u:User {username:$username})-[:OWNS]->(t:Todo)
                        RETURN t.id AS todoId,t.description AS description,t.completed AS completed
                        """;
                Result result = tx.run(query, parameters("username", username));

                List<Todo> todos = new ArrayList<>();
                while (result.hasNext()) {
                    Record record = result.next();
                    Todo todo = new Todo();
                    todo.setId(record.get("todoId").asLong());
                    todo.setDescription(record.get("description").asString());
                    todo.setCompleted(record.get("completed").asBoolean());
                    todos.add(todo);
                }
                return todos;
            });
        }
    }

    //UPDATE: Toggle T*do completion status
    @Override
    public Todo updateTodo(Long todoId, boolean completed) {
        try (Session session = driver.session()) {
            return session.executeWrite(tx -> {
                String query = """
                        MATCH (t:Todo {id:$todoId})
                        SET t.completed=$completed
                        RETURN t.id AS todoId, t.description AS description, t.completed AS completed
                        """;

                Record record = tx.run(query, parameters("todoId", todoId,
                        "completed", completed)).single();

                Todo todo = new Todo();
                todo.setId(record.get("todoId").asLong());
                todo.setCompleted(record.get("completed").asBoolean());
                todo.setDescription(record.get("description").asString());

                return todo;
            });
        }
    }

    //DELETE: Remove a T*do
    @Override
    public boolean deleteTodo(Long todoId) {
        try (Session session = driver.session()) {
            return session.executeWrite(tx -> {
                String query = """
                        MATCH (t:Todo {id:$todoId})
                        DETACH DELETE t;
                        """;

                tx.run(query, parameters("todoId", todoId)).consume();
                return true;
            });
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                String query = """
                        MATCH (u:User) 
                        RETURN u.id AS userId, u.username AS username;
                        """;
                Result result = tx.run(query);
                List<User> users = new ArrayList<>();
                while (result.hasNext()) {
                    Record record = result.next();
                    User user = new User();
                    user.setId(record.get("userId").asLong());
                    user.setUsername(record.get("username").asString());
                    users.add(user);
                }
                return users;
            });
        }
    }
    
}
