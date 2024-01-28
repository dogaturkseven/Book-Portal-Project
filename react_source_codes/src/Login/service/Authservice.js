import axios from "axios";
import LocalStorageUtil from "../util/LocalStorageUtil";

const AuthService = (function () {
  const _signin = async (credentials) => {
    let token = null;
    const auth = btoa(`${credentials.username}:${credentials.password}`)

    try {
      const response = await axios.post(
        "http://localhost:8090/basic/auth",
        credentials, {
          headers: {
            'Authorization': `Basic ${auth}` 
          }
        }
      );
      console.log("Response", response)
      console.log("response data", response.data)
      if (response && response.data) {
        token = auth
        LocalStorageUtil.setToken(token);
        console.log(token)
      }
    } catch (error) {
      console.log(error);
    }

    return token;
  };

  return {
    signin: _signin
  };
})();

export default AuthService;
