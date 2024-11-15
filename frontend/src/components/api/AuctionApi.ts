// AuctionApi.ts
const API_BASE_URL = 'http://localhost:8080/api/auctions'; // Replace with your backend URL

// Fetch all auctions
export const getAuctions = async (): Promise<any[]> => {
  try {
    const response = await fetch(`${API_BASE_URL}`);
    if (!response.ok) throw new Error('Failed to fetch auctions');
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return [];
  }
};

// Fetch a specific auction by ID
export const getAuctionById = async (auctionId: string): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}/${auctionId}`);
    if (!response.ok) throw new Error(`Failed to fetch auction with ID: ${auctionId}`);
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Create a new auction
export const createAuction = async (auction: { itemId: string; startPrice: number; endTime: string }): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(auction),
    });
    if (!response.ok) throw new Error('Failed to create auction');
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Update an existing auction
export const updateAuction = async (auctionId: string, auction: { startPrice: number; endTime: string }): Promise<any> => {
  try {
    const response = await fetch(`${API_BASE_URL}/${auctionId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(auction),
    });
    if (!response.ok) throw new Error(`Failed to update auction with ID: ${auctionId}`);
    return await response.json();
  } catch (error) {
    console.error('Error:', error);
    return null;
  }
};

// Delete an auction
export const deleteAuction = async (auctionId: string): Promise<void> => {
  try {
    const response = await fetch(`${API_BASE_URL}/${auctionId}`, { method: 'DELETE' });
    if (!response.ok) throw new Error(`Failed to delete auction with ID: ${auctionId}`);
  } catch (error) {
    console.error('Error:', error);
  }
};
