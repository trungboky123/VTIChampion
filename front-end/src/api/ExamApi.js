import axiosClient from "./axiosClient";
import { toast } from "react-toastify";

/**
 * @param {Object} params - Chứa các field: page, size, keyworld, classId, creatorId, startDate, endDate
 */

export async function getExams(params) {
  try {
    const queryParams = {
      ...params,
      page: params.page ? params.page - 1 : 0,
      size: params.size || 5,
    };

    const response = await axiosClient.get(`/exams`, { params: queryParams });

    // axiosClient (đã có interceptor) sẽ trả về trực tiếp đối tượng Page<ExamResponse>
    return response;
  } catch (error) {
    toast.error("Không thể tải danh sách bài thi!");
    throw error;
  }
}
