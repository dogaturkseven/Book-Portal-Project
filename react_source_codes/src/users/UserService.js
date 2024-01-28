import LocalStorageUtil from "../Login/util/LocalStorageUtil";

const token = LocalStorageUtil.getToken();

const fetchUsers = async (params) => {
    try {
        const queryString = new URLSearchParams({
            results: params.pagination.pageSize,
            page: params.pagination.current,
            ...params
        }).toString();

        const url = `http://localhost:8090/api/admin${window.location.pathname}?${queryString}`;

        const response = await fetch(url, {
            headers: {
                Authorization: `Basic ${token}`, // Replace 'username' and 'password' with your actual credentials
            },
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching users:", error);
        throw error;
    }
};

export { fetchUsers };
