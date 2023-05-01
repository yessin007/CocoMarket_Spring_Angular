import {PostLike} from './PostLike';
import {User} from './User';
import {PostComment} from './PostComment';
import {PostMedia} from './PostMedia';

export class  PosteStore{
    postId: number;
    body: string;
    createdAt: string;
    nb_Signal: number;
    nb_etoil: number;
    postTitle: string;
    postComments: PostComment[];
    postLikes: PostLike[];
    user: User;
    medias: PostMedia[];
}
