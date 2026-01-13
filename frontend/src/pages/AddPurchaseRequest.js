import { useEffect, useState } from "react";
import { apiFetch } from "../services/api";
import Navbar from "../components/Navbar";

function AddPurchaseOrder() {
  const [purchaseRequestId, setPurchaseRequestId] = useState("");
  const [vendorId, setVendorId] = useState("");
  const [amount, setAmount] = useState("");
  const [status, setStatus] = useState("DRAFT");
  const [comments, setComments] = useState("");
  const [terms, setTerms] = useState("");

  const [vendors, setVendors] = useState([]);
  const [prs, setPrs] = useState([]);

  useEffect(() => {
    apiFetch("http://localhost:8080/api/v1/vendors")
      .then((d) => {
        const list = Array.isArray(d) ? d : d.data || d.content || [];
        setVendors(list);
      })
      .catch(() => setVendors([]));

    apiFetch("http://localhost:8080/api/prs")
      .then((d) => {
        const list = Array.isArray(d) ? d : d.data || d.content || [];
        setPrs(list);
      })
      .catch(() => setPrs([]));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const params = new URLSearchParams({
      prId: purchaseRequestId,
      vendorId,
      comments,
      terms,
      amount: amount ? String(amount) : "",
      status,
    });

    try {
      // Use POST to the exact endpoint shown in Swagger (/create) with query params
      await apiFetch(`http://localhost:8080/api/v1/pos/create?${params.toString()}`, {
        method: "POST",
      });
      window.location.href = "/pos";
    } catch (err) {
      console.error("Error creating PO:", err);
      alert("Failed to create PO. See console for details.");
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
        <h2>Create Purchase Order</h2>

        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: "15px" }}>
            <label>Purchase Request</label>
            <select
              value={purchaseRequestId}
              onChange={(e) => setPurchaseRequestId(e.target.value)}
              required
              style={{ width: "100%", padding: "8px" }}
            >
              <option value="">-- select PR --</option>
              {prs.map((pr) => (
                <option key={pr.id} value={pr.id}>
                  {pr.id} - {pr.itemDescription ?? pr.title ?? ""}
                </option>
              ))}
            </select>
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Vendor</label>
            <select
              value={vendorId}
              onChange={(e) => setVendorId(e.target.value)}
              required
              style={{ width: "100%", padding: "8px" }}
            >
              <option value="">-- select vendor --</option>
              {vendors.map((v) => (
                <option key={v.id} value={v.id}>
                  {v.name}
                </option>
              ))}
            </select>
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Amount (optional)</label>
            <input
              type="number"
              min="0"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Comments</label>
            <textarea
              value={comments}
              onChange={(e) => setComments(e.target.value)}
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Terms</label>
            <textarea
              value={terms}
              onChange={(e) => setTerms(e.target.value)}
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "20px" }}>
            <label>Status</label>
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

          <button type="submit">Create PO</button>
        </form>
      </div>
    </div>
  );
}

export default AddPurchaseOrder;