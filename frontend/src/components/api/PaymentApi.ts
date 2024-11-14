// PaymentApi.ts
const API_BASE_URL = 'http://localhost:8080/api/payments'; // Replace with your backend URL

// Process a payment
export const processPayment = async (paymentDetails: { itemId: string; amount: number; userId: string }): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(paymentDetails),
    });
    if (!response.ok) throw new Error('Failed to process payment');
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Fetch payment status for a specific payment
export const getPaymentStatus = async (paymentId: string): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}/${paymentId}`);
    if (!response.ok) throw new Error(`Failed to fetch payment status for ID: ${paymentId}`);
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};
