import {SafeUrl} from '@angular/platform-browser';

export interface FileHandle{
    filefile: File;
    url: SafeUrl;
    preview: string;
}
