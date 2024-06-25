import React, { useState } from "react";
import axios from "axios";

const TodoForm = ({ onAdd }) => {
  const [task, setTask] = useState("");
  const addTodo = async () => {
    try {
      const response = await axios.post(`${process.env.REACT_APP_API_URL}/todos`, {
        task,
      });
      onAdd(response.data);
      setTask("");
    } catch (error) {
      console.error(error);
    }
  };
  
  return (
    <div>
      <input
        type="text"
        value={task}
        onChange={(e) => setTask(e.target.value)}
      />
      <button onClick={addTodo}>Add Todo</button>
    </div>
  );
};
export default TodoForm;
