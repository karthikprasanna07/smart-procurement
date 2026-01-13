import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { apiFetch } from "../services/api";
import Navbar from "../components/Navbar";

function EditPR() {
  const { id } = useParams();

  const [requestNumber, setRequestNumber] = useState("");
  const [itemDescription, setItemDescription] = useState("");
  const [quantity, setQuantity] = useState(1);
  const [budget, setBudget] = useState("");
  const [status, setStatus] = useState("DRAFT");
  const [requestedDate, setRequestedDate] = useState("");

  useEffect(() => {
    apiFetch(`http://localhost:8080/api/prs/${id}`)
      .then((data) => {
        setRequestNumber(data.requestNumber || "");
        setItemDescription(data.itemDescription || "");
        setQuantity(data.quantity ?? 1);
        setBudget(data.budget ?? "");
        setStatus(data.status ?? "DRAFT");
        setRequestedDate(
          data.requestedDate
            ? new Date(data.requestedDate).toISOString().slice(0, 16)
            : ""
        );
      })
      .catch((err) => {
        console.error("Error loading PR:", err);
      });
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const updatedPR = {
      requestNumber,
      itemDescription,
      quantity,
      budget,
      status,
      requestedDate: requestedDate ? new Date(requestedDate).toISOString() : null,
    };

    try {
      await apiFetch(`http://localhost:8080/api/prs/${id}`, {
        method: "PUT",
        body: JSON.stringify(updatedPR),
      });

      window.location.href = "/prs";
    } catch (err) {
      console.error("Error updating PR:", err);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href = "/login";
  };

  return (
    <div>
      <Navbar onLogout={handleLogout} />

      <div style={{ padding: "40px", maxWidth: "600px" }}>
        <h2>Edit Purchase Request</h2>

        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: "15px" }}>
            <label>PR No</label>
            <br />
            <input
              type="text"
              value={requestNumber}
              onChange={(e) => setRequestNumber(e.target.value)}
              required
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Item Description</label>
            <br />
            <textarea
              value={itemDescription}
              onChange={(e) => setItemDescription(e.target.value)}
              required
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Quantity</label>
            <br />
            <input
              type="number"
              min="1"
              value={quantity}
              onChange={(e) => setQuantity(Number(e.target.value))}
              required
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Budget</label>
            <br />
            <input
              type="text"
              value={budget}
              onChange={(e) => setBudget(e.target.value)}
              required
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Requested Date</label>
            <br />
            <input
              type="datetime-local"
              value={requestedDate}
              onChange={(e) => setRequestedDate(e.target.value)}
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "20px" }}>
            <label>Status</label>
            <br />
            <select
              value={status}
              onChange={(e) => setStatus(e.target.value)}
              style={{ width: "100%", padding: "8px" }}
            >
              <option value="DRAFT">DRAFT</option>
              <option value="PENDING_APPROVAL">PENDING_APPROVAL</option>
              <option value="APPROVED">APPROVED</option>
              <option value="REJECTED">REJECTED</option>
            </select>
          </div>

          <button type="submit">Update PR</button>
        </form>
      </div>
    </div>
  );
}

export default EditPR;