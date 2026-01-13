import { useEffect, useState } from "react";
import { apiFetch } from "../services/api";
import Navbar from "../components/Navbar";

const thStyle = {
  padding: "10px",
  borderBottom: "1px solid #ddd",
  textAlign: "left",
};

const tdStyle = {
  padding: "10px",
  borderBottom: "1px solid #ddd",
};

function PurchaseRequests() {
  const [prs, setPrs] = useState([]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href = "/login";
  };

  // ✅ SINGLE source of truth: fetch from backend
  const fetchPRs = () => {
    apiFetch("http://localhost:8080/api/prs")
      .then((data) => {
        const list = Array.isArray(data)
          ? data
          : data.data || data.content || [];
        setPrs(list);
      })
      .catch(() => setPrs([]));
  };

  // 🔹 Initial load
  useEffect(() => {
    fetchPRs();
  }, []);

  // 🔹 Approve / Reject / Send for Approval
  const updateStatus = async (id, newStatus) => {
    try {
      await apiFetch(`http://localhost:8080/api/prs/${id}`, {
        method: "PUT",
        body: JSON.stringify({ status: newStatus }),
      });

      // ✅ IMPORTANT: re-fetch from backend
      fetchPRs();

    } catch (err) {
      console.error("Failed to update PR status:", err);
      alert("Failed to update PR status");
    }
  };

    const editpr = (id) => {
      window.location.href = `/prs/edit/${id}`;
    };

  return (
    <div>
      <Navbar onLogout={handleLogout} />

      <div style={{ padding: "40px" }}>
        <h2>Purchase Requests</h2>

        <button
          onClick={() => (window.location.href = "/prs/add")}
          style={{ marginBottom: "20px" }}
        >
          Add PR
        </button>

        <table style={{ width: "100%", borderCollapse: "collapse" }}>
          <thead>
            <tr style={{ backgroundColor: "#f5f5f5" }}>
              <th style={thStyle}>PR No</th>
              <th style={thStyle}>Item</th>
              <th style={thStyle}>Quantity</th>
              <th style={thStyle}>Budget</th>
              <th style={thStyle}>Status</th>
              <th style={thStyle}>Requested Date</th>
              <th style={thStyle}>Actions</th>
            </tr>
          </thead>

          <tbody>
            {Array.isArray(prs) &&
              prs.map((pr) => (
                <tr key={pr.id}>
                  <td style={tdStyle}>{pr.requestNumber}</td>
                  <td style={tdStyle}>{pr.itemDescription}</td>
                  <td style={tdStyle}>{pr.quantity}</td>
                  <td style={tdStyle}>{pr.budget}</td>
                  <td style={tdStyle}>{pr.status}</td>
                  <td style={tdStyle}>
                    {pr.requestedDate
                      ? new Date(pr.requestedDate).toLocaleString()
                      : "-"}
                  </td>

                  <td style={tdStyle}>
                    {pr.status === "DRAFT" && (
                      <button
                        onClick={() =>
                          updateStatus(pr.id, "PENDING_APPROVAL")
                        }
                      >
                        Send for Approval
                      </button>
                    )}

                    {pr.status === "PENDING_APPROVAL" && (
                      <>
                        <button
                          onClick={() =>
                            updateStatus(pr.id, "APPROVED")
                          }
                          style={{ marginRight: "8px" }}
                        >
                          Approve
                        </button>
                        <button
                          onClick={() =>
                            updateStatus(pr.id, "REJECTED")
                          }
                        >
                          Reject
                        </button>
                        
                      </>
                    )}
                    <button
                          onClick={() =>
                            editpr(pr.id)
                          }
                        >
                          Edit
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

export default PurchaseRequests;
