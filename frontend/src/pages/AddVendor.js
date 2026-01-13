import { useState } from "react";
import { apiFetch } from "../services/api";
import Navbar from "../components/Navbar";

function AddVendor() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");
  const [gstNumber, setGstNumber] = useState("");
  const [rating, setRating] = useState(1);
  const [status, setStatus] = useState("ACTIVE");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const vendorData = {
      name,
      email,
      phone,
      address,
      gstNumber,
      rating,
      status,
    };

    try {
      await apiFetch("http://localhost:8080/api/v1/vendors", {
        method: "POST",
        body: JSON.stringify(vendorData),
      });

      // Redirect back to vendors list
      window.location.href = "/vendors";
    } catch (err) {
      console.error("Error adding vendor:", err);
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
        <h2>Add Vendor</h2>

        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: "15px" }}>
            <label>Name</label>
            <br />
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Email</label>
            <br />
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Phone</label>
            <br />
            <input
              type="text"
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
              placeholder="10 digit number"
              required
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Address</label>
            <br />
            <textarea
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              required
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>GST Number</label>
            <br />
            <input
              type="text"
              value={gstNumber}
              onChange={(e) => setGstNumber(e.target.value)}
              placeholder="15 character GST number"
              required
              style={{ width: "100%", padding: "8px" }}
            />
          </div>

          <div style={{ marginBottom: "15px" }}>
            <label>Rating (1 to 5)</label>
            <br />
            <input
              type="number"
              min="1"
              max="5"
              value={rating}
              onChange={(e) => setRating(Number(e.target.value))}
              required
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
              <option value="ACTIVE">ACTIVE</option>
              <option value="INACTIVE">INACTIVE</option>
            </select>
          </div>

          <button type="submit">Save Vendor</button>
        </form>
      </div>
    </div>
  );
}

export default AddVendor;
