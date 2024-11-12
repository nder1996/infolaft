export interface ApiResponse<T> {
    meta: Meta;
    data: T;
    error?: ErrorDetails;
 }
 
 export interface Meta {
    message: string;
    statusCode: number;
 }
 
 export interface ErrorDetails {
    code: string;
    description: string;
 }