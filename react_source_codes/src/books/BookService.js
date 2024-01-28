import LocalStorageUtil from "../Login/util/LocalStorageUtil";

const token = LocalStorageUtil.getToken();

const fetchBooks = async (params) => {
    try {
        const queryString = new URLSearchParams({
            results: params.pagination.pageSize,
            page: params.pagination.current,
            ...params
        }).toString();


        const response = await fetch(`http://localhost:8090/api/admin${window.location.pathname}?${queryString}`, {
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
        console.error("Error fetching books:", error);
        throw error;
    }
};

export { fetchBooks };
