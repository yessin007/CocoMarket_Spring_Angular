import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-announcement-bar',
  templateUrl: './announcement-bar.component.html',
  styleUrls: ['./announcement-bar.component.scss']
})
export class AnnouncementBarComponent implements OnInit{
  announcementBar: HTMLElement;
  texts: NodeList;
  index = 0;
  ngOnInit(): void {
    this.announcementBar = document.querySelector('.announcement-bar');
    this.texts = this.announcementBar.querySelectorAll('p');
    this.animateText();
  }

  animateText() {
    if (this.index === this.texts.length) {
      this.index = 0;
    }

    const currentText = this.texts[this.index] as HTMLElement;
    const nextText = this.texts[this.index === this.texts.length - 1 ? 0 : this.index + 1] as HTMLElement;

    currentText.classList.remove('animate');
    nextText.classList.add('animate');

    this.index++;

    setTimeout(() => {
      currentText.classList.add('hide');
      nextText.classList.remove('hide');

      setTimeout(() => {
        this.animateText();
      }, 500);
    }, 2000);
  }
}
