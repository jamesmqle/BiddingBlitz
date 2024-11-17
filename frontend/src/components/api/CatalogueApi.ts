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