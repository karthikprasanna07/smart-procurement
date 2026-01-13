import { Link } from "react-router-dom";

function Navbar({ onLogout }) {
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: "15px 40px",
        borderBottom: "1px solid #ddd",
        backgroundColor: "white",
      }}
    >
      {/* Left side navigation links */}
      <div style={{ display: "flex", gap: "20px" }}>
        <Link to="/dashboard" style={linkStyle}>Dashboard</Link>
        <Link to="/vendors" style={linkStyle}>Vendors</Link>
        <Link to="/prs" style={linkStyle}>PRs</Link>
        <Link to="/pos" style={linkStyle}>POs</Link>

      </div>

      {/* Right side logout */}
      <button onClick={onLogout}>Logout</button>
    </div>
  );
}

const linkStyle = {
  textDecoration: "none",
  color: "#0d6efd",
  fontWeight: "500",
};

export default Navbar;
