import { useEffect, useState } from "react";
import { apiFetch } from "../services/api";
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

function Vendors() {
    const [vendors, setVendors] = useState([]);

    const handleLogout = () => {
        localStorage.removeItem("token");
        window.location.href = "/login";
    };
    const handleDelete = async (id) => {
        const confirmDelete = window.confirm(
            "Are you sure want to deactivate this vendor?"
        );

        if (!confirmDelete) return;

        try {
            await apiFetch(`http://localhost:8080/api/v1/vendors/${id}`, {
                method: "DELETE",
            });

            // Remove deleted vendor from UI immediately
            setVendors((prev) => prev.filter((v) => v.id !== id));
        } catch (err) {
            console.error("Error deleting vendor:", err);
        }
    };


    useEffect(() => {
        apiFetch("http://localhost:8080/api/v1/vendors")
            .then((data) => {
                // 👇 GUARANTEE vendors is always an array
                if (Array.isArray(data)) {
                    setVendors(data);
                } else if (Array.isArray(data.data)) {
                    setVendors(data.data);
                } else if (Array.isArray(data.content)) {
                    setVendors(data.content);
                } else {
                    setVendors([]);
                }
            })
            .catch(() => {
                setVendors([]);
            });
    }, []);


    return (
        <div>
            <Navbar onLogout={handleLogout} />

            <div style={{ padding: "40px" }}>
                <h2>Vendors</h2>
                <button
                    onClick={() => window.location.href = "/vendors/add"}
                    style={{ marginBottom: "20px" }}
                >
                    Add Vendor
                </button>


                <table style={{ width: "100%", borderCollapse: "collapse" }}>
                    <thead>
                        <tr style={{ backgroundColor: "#f5f5f5" }}>
                            <th style={thStyle}>Name</th>
                            <th style={thStyle}>Email</th>
                            <th style={thStyle}>Phone</th>
                            <th style={thStyle}>Status</th>
                            <th style={thStyle}>Actions</th>
                        </tr>
                    </thead>

                    <tbody>
                        {Array.isArray(vendors) && vendors.map((vendor) => (
                            <tr key={vendor.id}>
                                <td style={tdStyle}>{vendor.name}</td>
                                <td style={tdStyle}>{vendor.email}</td>
                                <td style={tdStyle}>{vendor.phone}</td>
                                <td style={tdStyle}>{vendor.status}</td>
                                <td style={tdStyle}>
                                    <button
                                        onClick={() => window.location.href = `/vendors/edit/${vendor.id}`}
                                        style={{ marginRight: "10px" }}
                                    >
                                        Edit
                                    </button>

                                    <button
                                        onClick={() => handleDelete(vendor.id)}
                                    >
                                        Deactivate
                                    </button>
                                </td>


                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Vendors;
