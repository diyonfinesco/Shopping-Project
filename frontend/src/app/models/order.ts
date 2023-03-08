import { Item } from "./item";

export interface Order {
    id: string,
    items: Item[],
    status: string
}