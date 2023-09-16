import {PostLike} from './PostLike';
import {PostMedia} from './PostMedia';
import {User} from '../models/User';
import {PostComment} from './PostComment';

export class  PosteStore{
    postId: number;
    body: string;
    createdAt: Date;
    nb_Signal: number;
    nb_etoil: number;
    postTitle: string;
    postComments: PostComment[];
    postLikes!: PostLike[];
    user: User;
    medias: PostMedia[];
}
