// AuctionApi.ts
const API_BASE_URL = 'http://localhost:8080/api/auction'; // Replace with your backend URL

export const placeBid = async (itemId: number, bidAmount: number, userId: number) => {
  const response = await fetch(`${API_BASE_URL}/bid/${itemId}?bidAmount=${bidAmount}&userId=${userId}`, {
    method: "POST",
  });
  
  if (!response.ok) {
    const errorMessage = await response.text();
    throw new Error(errorMessage);
  }
  
  return response.text();
};
