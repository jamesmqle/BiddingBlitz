// CatalogueApi.ts
const API_BASE_URL = 'http://localhost:8080/api/catalogue'; // Replace with your backend URL

// Function to get items with an optional keyword
export const getItemDetails = async (keyword: string | null): Promise<any> => {
  try {
    const url = keyword ? `${API_BASE_URL}/search?keyword=${keyword}` : `${API_BASE_URL}/search`;
    const response = await fetch(url);
    
    if (!response.ok) {
      throw new Error('Failed to fetch items');
    }

    return await response.json();
  } catch (error) {
    console.error("Error fetching items:", error);
    throw error;
  }
};

// Function to get details of a specific item by its ID
export const getItemById = async (itemId: number): Promise<any> => {
  try {
    const url = `${API_BASE_URL}/item/${itemId}`;
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error('Failed to fetch item details');
    }

    return await response.json();
  } catch (error) {
    console.error("Error fetching item details:", error);
    throw error;
  }
};