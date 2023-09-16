import {Component, OnInit} from '@angular/core';
import {StoreService} from '../../../shared/services/store/store.service';
import {filter, Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {PosteStore} from '../../../shared/classes/PostStore';
import {PostLike} from '../../../shared/classes/PostLike';
import {PostComment} from '../../../shared/classes/PostComment';
@Component({
  selector: 'app-post-store',
  templateUrl: './post-store.component.html',
  styleUrls: ['./post-store.component.scss']
})
export class PostStoreComponent implements OnInit{

  constructor( private rr: Router  , private router: Router, private service: StoreService ){}
  private routeSub: Subscription;
  listPost: PosteStore[];
  term: string;
  postLike: PostLike = new PostLike();
  post1: PosteStore = new PosteStore();
  comment: PostComment = new PostComment();

  protected readonly filter = filter;
  ngOnInit(): void {
    this.routeSub = this.service.getPosts().subscribe(res => {
      console.log(res);
      this.listPost = res;
    });
  }

  addLikePost(id: string) {
    this.service.addPostLike(id, this.postLike).subscribe(p => {
      console.log(this.postLike);
      this.routeSub = this.service.getPosts().subscribe(res => {
        console.log(res);
        this.listPost = res;
      });
    });
  }
  addDisLikePost(id: string) {
    this.service.addPostDisLike(id, this.postLike).subscribe(p => {
      console.log(this.postLike);
      this.routeSub = this.service.getPosts().subscribe(res => {
        console.log(res);
        this.listPost = res;
      });
    });
  }
  ratePost(id: string, x: string) {
    this.service.ratePost(id, x).subscribe(p => {
      console.log(this.post1);
      this.routeSub = this.service.getPosts().subscribe(res => {
        console.log(res);
        this.listPost = res;
      });
    });
  }
  reportPost(id: string) {
    this.service.reportPost(id).subscribe(p => {
      console.log('reporte');
      this.router.navigate(['collection/post-store']).then(() => {
        window.location.reload();
      });
    });
  }
  showPostDetails(storeId) {
    this.rr.navigate(['/shop/collection/post-detail', {storeId}]);
  }
}
