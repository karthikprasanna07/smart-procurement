import React, { useState } from "react";
import "./login.css";
import { loginUser } from "../services/authService";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleLogin = async () => {
    setErrorMessage("");
    const loginData = { username, password };

    try {
      const result = await loginUser(loginData);

      if (result && result.token) {
        localStorage.setItem("token", result.token);
        window.location.href = "/dashboard";
      } else {
        const msg = (result && result.message) || "User does not exist or invalid credentials";
        setErrorMessage(msg);
      }
    } catch (error) {
      console.error("Login error:", error);

      let message = "Something went wrong. Please try again.";

      if (error.response) {
        if (error.response.status === 401 || error.response.status === 403) {
          message = "User does not exist or invalid credentials";
        } else if (error.message) {
          message = error.message;
        }
      } else if (error.message) {
        message = error.message;
      }

      setErrorMessage(message);
    }
  };

  return (
    <div className="login-container">
      <div className="login-left">
        <h3>Smart Procurement</h3>
      </div>

      <div className="login-right">
        <div className="login-card">
          <h2>Welcome !!</h2>
          <p>Sign in to get started</p>

          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          {errorMessage && <div className="login-error">{errorMessage}</div>}

          <button onClick={handleLogin}>Login</button>
        </div>
      </div>
    </div>
  );
}

export default Login;