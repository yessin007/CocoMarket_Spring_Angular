import {Component, OnInit} from '@angular/core';
import {StoreService} from '../../../services/store/store.service';
import {Router} from '@angular/router';
import {PosteStore} from '../../../models/PostStore';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.scss']
})
export class AddPostComponent implements OnInit{
  post: PosteStore = new PosteStore();
  errorMessage = 'null';
  fileToUpload: File | null = null;
  imagenMin: File;

  constructor(private cs: StoreService, ) {
  }

  ngOnInit(): void {
  }
  addnewpost() {
    this.cs.addPost(this.post).subscribe(data => {
          this.post = data ,
              console.log(data);
          this.errorMessage = 'valide'; },
        err => {
          if (err?.status === 424) {
            this.errorMessage = 'Bad Word used';
          } else if (err?.status === 400) {
            this.errorMessage = 'Email already exists';
          }
        }

    );
  }
}
