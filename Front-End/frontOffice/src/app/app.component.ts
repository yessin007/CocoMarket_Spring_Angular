import { Component, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { LoadingBarService } from '@ngx-loading-bar/core';
import {map, delay, withLatestFrom, catchError} from 'rxjs/operators';
import { TranslateService } from '@ngx-translate/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  // For Progressbar
  loaders = this.loader.progress$.pipe(
    delay(1000),
    withLatestFrom(this.loader.progress$),
    map(v => v[1]),
  );

  apiLoaded: Observable<boolean>;

  constructor(@Inject(PLATFORM_ID) private platformId: Object,
              private loader: LoadingBarService, translate: TranslateService, httpClient: HttpClient) {
    this.apiLoaded = httpClient.jsonp('https://maps.googleapis.com/maps/api/js?key=AIzaSyAvHALSEQwNE3b-b7eHSZIDv-KK1wr7CRQZ', 'callback')
        .pipe(
            map(() => true),
            catchError(() => of(false)),
        );
    if (isPlatformBrowser(this.platformId)) {
      translate.setDefaultLang('en');
      translate.addLangs(['en', 'fr']);
    }
  }

}
