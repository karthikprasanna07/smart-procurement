import { useEffect, useState } from "react";
import { apiFetch } from "../services/api";
import "./Dashboard.css";
import Navbar from "../components/Navbar";


const thStyle = {
    padding: "10px",
    textAlign: "left",
    borderBottom: "1px solid #ddd",
};

const tdStyle = {
    padding: "10px",
    borderBottom: "1px solid #ddd",
};

function Dashboard() {
    const [vendors, setVendors] = useState([]);

    // 🔢 Derived values for cards
    const activeVendors = vendors.filter(
        v => v.status && v.status.toUpperCase() === "ACTIVE"
    ).length;

    const inactiveVendors = vendors.filter(
        v => v.status && v.status.toUpperCase() === "INACTIVE"
    ).length;


    const handleLogout = () => {
        localStorage.removeItem("token");
        window.location.href = "/login";
    };

    useEffect(() => {
        apiFetch("http://localhost:8080/api/v1/vendors")
            .then((data) => {
                setVendors(data);
            })
            .catch((err) => {
                console.error("API error:", err);
            });
    }, []);

    return (
        <div>
            {/* Top Navigation Bar */}
            <Navbar onLogout={handleLogout} />

            {/* Dashboard Content */}
            <div className="dashboard-container">

                {/* Summary cards */}
                <div className="cards">
                    <div className="card">
                        <h3>Total Vendors</h3>
                        <p>{vendors.length}</p>
                    </div>

                    <div className="card">
                        <h3>Active Vendors</h3>
                        <p>{activeVendors}</p>
                    </div>

                    <div className="card">
                        <h3>Inactive Vendors</h3>
                        <p>{inactiveVendors}</p>
                    </div>
                </div>

                <hr />

            </div>
        </div>
    );
}

export default Dashboard;
