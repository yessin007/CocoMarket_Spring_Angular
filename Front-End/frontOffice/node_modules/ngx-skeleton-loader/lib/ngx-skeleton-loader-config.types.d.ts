import { InjectionToken } from '@angular/core';
export declare type NgxSkeletonLoaderConfigTheme = {
    [k: string]: any;
} | null;
export interface NgxSkeletonLoaderConfig {
    appearance: 'circle' | 'line' | '';
    animation: 'progress' | 'progress-dark' | 'pulse' | 'false' | false;
    theme: NgxSkeletonLoaderConfigTheme;
    loadingText: string;
    count: number;
    ariaLabel: string;
}
export declare const NGX_SKELETON_LOADER_CONFIG: InjectionToken<NgxSkeletonLoaderConfig>;
