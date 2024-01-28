import LocalStorageUtil from "../Login/util/LocalStorageUtil";

const token = LocalStorageUtil.getToken();

const fetchBooksForUser = async (params) => {
  try {
    const queryString = new URLSearchParams({
      results: params.pagination.pageSize,
      page: params.pagination.current,
      ...params
    }).toString();

    const url = `http://localhost:8090/api${window.location.pathname}?${queryString}`;

    const response = await fetch(url, {
      headers: {
        Authorization: `Basic ${token}`, 
    },
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching books for user:", error);
    throw error;
  }
};

export { fetchBooksForUser };
