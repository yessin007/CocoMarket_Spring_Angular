import {User} from "../models/User";
import {Product} from "./product";

export interface Subscription{
    subscriptionId?: number;
    user?: User;
    product?: Product;
    subMonths?: number;
    remainingDaysINMonth?: number;
    prize?: Product;
    dateOfSubCreation?: Date;
    dateEndOfSubscription?: Date;
}
