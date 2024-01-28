import axios from "axios";

const addToReadList = async () => {
    debugger;
    const response = await axios.post("http://localhost:8090/api"+ window.location.pathname)

    if(!response){
        console.log("hata");
        return;
    }

    return response.data;
};


export {addToReadList};