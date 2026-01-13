import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/login";
import Dashboard from "./pages/Dashboard";
import Vendors from "./pages/Vendors";
import ProtectedRoute from "./components/ProtectedRoute";
import AddVendor from "./pages/AddVendor";
import EditVendor from "./pages/EditVendor";
import PurchaseRequests from "./pages/PurchaseRequests";
import AddPurchaseRequest from "./pages/AddPurchaseRequest";
import EditPR from "./pages/EditPR";
import PurchaseOrders from "./pages/PurchaseOrders";
import AddPurchaseOrder from "./pages/AddPurchaseOrder";






function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Login routes */}
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />

        {/* Dashboard route */}
        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <Dashboard />
            </ProtectedRoute>
          }
        />

        {/* Vendors route */}
        <Route
          path="/vendors"
          element={
            <ProtectedRoute>
              <Vendors />
            </ProtectedRoute>
          }
        />
        <Route
          path="/vendors/add"
          element={
            <ProtectedRoute>
              <AddVendor />
            </ProtectedRoute>
          }
        />
        <Route
          path="/vendors/edit/:id"
          element={
            <ProtectedRoute>
              <EditVendor />
            </ProtectedRoute>
          }
        />
        <Route
          path="/prs"
          element={
            <ProtectedRoute>
              <PurchaseRequests />
            </ProtectedRoute>
          }
        />

        <Route
          path="/prs/add"
          element={
            <ProtectedRoute>
              <AddPurchaseRequest />
            </ProtectedRoute>
          }
        />
        <Route
          path="/prs/edit/:id"
          element={
            <ProtectedRoute>
              <EditPR />
            </ProtectedRoute>
          }
        />

        <Route
          path="/pos"
          element={
            <ProtectedRoute>
              <PurchaseOrders />
            </ProtectedRoute>
          }
        />
        <Route
          path="/pos/create"
          element={
            <ProtectedRoute>
              <AddPurchaseOrder />
            </ProtectedRoute>
          }
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;
