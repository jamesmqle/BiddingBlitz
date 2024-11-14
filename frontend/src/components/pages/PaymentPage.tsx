import React, { useState } from "react";

const PaymentPage: React.FC = () => {
  const [shippingMethod, setShippingMethod] = useState("standard");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    alert("Payment Successful!");
  };

  return (
    <div>
      <h2>Payment Page</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Shipping Method:
          <select
            value={shippingMethod}
            onChange={(e) => setShippingMethod(e.target.value)}
          >
            <option value="standard">Standard</option>
            <option value="expedited">Expedited</option>
          </select>
        </label>
        <button type="submit">Complete Payment</button>
      </form>
    </div>
  );
};

export default PaymentPage;
