// This function talks to the backend login API
export const loginUser = async (loginData) => {
  const response = await fetch("http://localhost:8080/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginData),
  });

  const data = await response.json();

  if (!response.ok) {
    const error = new Error(data.message || "Login failed");
    error.response = { status: response.status, data };
    throw error;
  }

  return data;
};