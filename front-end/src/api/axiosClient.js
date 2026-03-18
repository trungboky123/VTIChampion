import axios from "axios";

const axiosClient = axios.create({
  baseURL: "http://localhost:8080/api/v1", // URL Backend thật của bạn
  headers: {
    "Content-Type": "application/json",
  },
});

// Trả về data trực tiếp để các hàm sau không phải .data nữa
axiosClient.interceptors.response.use(
  (response) => response,
  (error) => {
    return Promise.reject(error);
  },
);

export default axiosClient;
