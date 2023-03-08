export interface User {
    name: string
    username: string,
    role: Role
}

export enum Role {
    CUSTOMER = "CUSTOMER",
    ADMIN = "ADMIN"
}