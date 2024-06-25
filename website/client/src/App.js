import React, { useState, useEffect } from "react";
import axios from "axios";
import TodoForm from './TodoForm';
import "./styles/App.css"

const App = () => {
  const [todos, setTodos] = useState([]);
  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/todos`)
      .then((response) => setTodos(response.data))
      .catch((error) => console.error(error));
  }, []);
  
  const addTodo = (newTodo) => {
    setTodos([...todos, newTodo]);
  };
  return (
    <div className="container">
      <h1>MERN Stack Todo App</h1>
      <TodoForm onAdd={addTodo} />
      <ul>
        {todos.map((todo) => (
          <li key={todo._id}>{todo.task}</li>
        ))}
      </ul>
    </div>
  );
};
export default App;
