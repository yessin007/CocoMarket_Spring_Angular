import {Component, OnInit} from '@angular/core';
import {StoreService} from '../../../shared/services/store/store.service';
import {Subscription} from 'rxjs';
import {PostComment} from "../../../shared/classes/PostComment";
import {PosteStore} from "../../../shared/classes/PostStore";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit{
  errorComment = '';
  commenttest = '';
  comment: PostComment = new PostComment();
  private routeSub: Subscription;
  listPost: PosteStore[];
  constructor( private service: StoreService) {
  }
  ngOnInit(): void {
  }
  addComment(id: string) {
    this.service.addCommentPst(id, this.comment).subscribe(data => {
          this.routeSub = this.service.getPosts().subscribe(res => {
            console.log(res);
            this.listPost = res;
          });
        },
        err => {
          if (err?.status === 424) {
            this.errorComment = 'Bad Word used';
          } else if (err?.status === 400) {
            this.errorComment = 'Email already exists';
          }
        }
    );
  }

}
