import {SafeUrl} from '@angular/platform-browser';

export interface FileHandleMal{
    filemal: File;
    url: SafeUrl;
    preview?: string;
}
