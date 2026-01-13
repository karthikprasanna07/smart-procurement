import { Navigate } from "react-router-dom";

// This component protects routes that need authentication
function ProtectedRoute({ children }) {
  // Read token from browser storage
  const token = localStorage.getItem("token");

  // If no token, redirect to login
  if (!token) {
    return <Navigate to="/login" />;
  }

  // If token exists, allow access
  return children;
}

export default ProtectedRoute;
