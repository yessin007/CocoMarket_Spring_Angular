import {PosteStore} from './PostStore';
import {User} from '../models/User';


export class PostComment {
    postCommentId: number;
    commentBody: string;
    commentedAt: string;
    user: User;
    postComments: PostComment[];
    postStore : PosteStore ;
}