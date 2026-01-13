import { useEffect, useState } from "react";
import { apiFetch } from "../services/api";
import Navbar from "../components/Navbar";
import { Link } from "react-router-dom";

const thStyle = { padding: "10px", borderBottom: "1px solid #ddd", textAlign: "left" };
const tdStyle = { padding: "10px", borderBottom: "1px solid #ddd" };

function PurchaseOrders() {
    const [pos, setPos] = useState([]);

    const handleLogout = () => {
        localStorage.removeItem("token");
        window.location.href = "/login";
    };

    const fetchPOs = () => {
        apiFetch("http://localhost:8080/api/v1/pos")
            .then((data) => {
                const list = Array.isArray(data) ? data : data.data || data.content || [];
                setPos(list);
            })
            .catch((err) => {
                console.error("Failed to load POs:", err);
                setPos([]);
            });
    };

    useEffect(() => {
        fetchPOs();
    }, []);

    return (
        <>
            <Navbar onLogout={handleLogout} />

            <div style={{ padding: "20px" }}>
                <h2>Purchase Orders</h2>

                <Link to="/pos/create">
                    <button style={{ marginBottom: "15px" }}>Add PO</button>
                </Link>

                <table style={{ width: "100%", borderCollapse: "collapse" }}>
                    <thead>
                        <tr style={{ backgroundColor: "#f5f5f5" }}>
                            <th style={thStyle}>PO ID</th>
                            <th style={thStyle}>PR ID</th>
                            <th style={thStyle}>Vendor</th>
                            <th style={thStyle}>Status</th>
                            <th style={thStyle}>Created Date</th>
                        </tr>
                    </thead>

                    <tbody>
                        {Array.isArray(pos) &&
                            pos.map((po) => (
                                <tr key={po.id}>
                                    <td style={tdStyle}>{po.id}</td>
                                    <td style={tdStyle}>{po.purchaseRequest?.id ?? po.purchaseRequestId ?? "-"}</td>
                                    <td style={tdStyle}>{po.vendor?.name ?? po.vendorName ?? "-"}</td>
                                    <td style={tdStyle}>{po.status}</td>
                                    <td style={tdStyle}>
                                        {po.createdDate ? new Date(po.createdDate).toLocaleDateString('en-GB').replace(/\//g, '-') : "-"}
                                    </td>
                                </tr>
                            ))}
                    </tbody>
                </table>
            </div>
        </>
    );
}

export default PurchaseOrders;