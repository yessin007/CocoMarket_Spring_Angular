import {User} from './User';


export class PostComment {
    postCommentId: number;
    commentBody: string;
    commentedAt: string;
    user: User;
    postComments: PostComment[];
}
