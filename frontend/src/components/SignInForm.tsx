import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "./api/UserApi";

const SignInForm: React.FC = () => {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setCredentials({ ...credentials, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await loginUser(credentials);
      setMessage("Login successful!");
      console.log("Token:", response.data); // Handle token or response as needed
      localStorage.setItem("userId", response.data.userId); // set userId in session storage
      navigate("/");
    } catch (error: any) {
      setMessage("Login failed: " + (error.response?.data || error.message));
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Login</h2>
      <div>
        <input name="username" placeholder="Username" onChange={handleChange} />
        <input
          name="password"
          type="password"
          placeholder="Password"
          onChange={handleChange}
        />
      </div>
      <button type="submit">Login</button>
      <p>{message}</p>
    </form>
  );
};

export default SignInForm;
