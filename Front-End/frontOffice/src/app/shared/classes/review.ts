import {User} from '../models/User';

export interface Review{

    reviewId?: number;
    reviewTitle?: string;
    reviewText?: string;
    verified?: boolean;
    rating?: number;
    createdAt?: Date;
    name?: string;
    user?: User;
}
