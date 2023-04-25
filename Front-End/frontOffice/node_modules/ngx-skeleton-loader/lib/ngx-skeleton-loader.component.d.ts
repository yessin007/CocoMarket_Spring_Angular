import { OnInit, OnDestroy, AfterViewInit, OnChanges, SimpleChanges } from '@angular/core';
import { NgxSkeletonLoaderConfig, NgxSkeletonLoaderConfigTheme } from './ngx-skeleton-loader-config.types';
import * as i0 from "@angular/core";
export declare class NgxSkeletonLoaderComponent implements OnInit, AfterViewInit, OnDestroy, OnChanges {
    static ngAcceptInputType_count: number | string;
    static ngAcceptInputType_animation: boolean | string;
    count: NgxSkeletonLoaderConfig['count'];
    loadingText: NgxSkeletonLoaderConfig['loadingText'];
    appearance: NgxSkeletonLoaderConfig['appearance'];
    animation: NgxSkeletonLoaderConfig['animation'];
    ariaLabel: NgxSkeletonLoaderConfig['ariaLabel'];
    theme: NgxSkeletonLoaderConfigTheme;
    items: Array<any>;
    constructor(config?: NgxSkeletonLoaderConfig);
    ngOnInit(): void;
    private validateInputValues;
    ngOnChanges(changes: SimpleChanges): void;
    ngAfterViewInit(): void;
    ngOnDestroy(): void;
    static ɵfac: i0.ɵɵFactoryDeclaration<NgxSkeletonLoaderComponent, [{ optional: true; }]>;
    static ɵcmp: i0.ɵɵComponentDeclaration<NgxSkeletonLoaderComponent, "ngx-skeleton-loader", never, { "count": "count"; "loadingText": "loadingText"; "appearance": "appearance"; "animation": "animation"; "ariaLabel": "ariaLabel"; "theme": "theme"; }, {}, never, never>;
}
