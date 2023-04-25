import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxSkeletonLoaderComponent } from './ngx-skeleton-loader.component';
import { NGX_SKELETON_LOADER_CONFIG } from './ngx-skeleton-loader-config.types';
import * as i0 from "@angular/core";
export class NgxSkeletonLoaderModule {
    static forRoot(config) {
        return {
            ngModule: NgxSkeletonLoaderModule,
            providers: [{ provide: NGX_SKELETON_LOADER_CONFIG, useValue: config }],
        };
    }
}
/** @nocollapse */ /** @nocollapse */ NgxSkeletonLoaderModule.ɵfac = i0.ɵɵngDeclareFactory({ minVersion: "12.0.0", version: "13.1.1", ngImport: i0, type: NgxSkeletonLoaderModule, deps: [], target: i0.ɵɵFactoryTarget.NgModule });
/** @nocollapse */ /** @nocollapse */ NgxSkeletonLoaderModule.ɵmod = i0.ɵɵngDeclareNgModule({ minVersion: "12.0.0", version: "13.1.1", ngImport: i0, type: NgxSkeletonLoaderModule, declarations: [NgxSkeletonLoaderComponent], imports: [CommonModule], exports: [NgxSkeletonLoaderComponent] });
/** @nocollapse */ /** @nocollapse */ NgxSkeletonLoaderModule.ɵinj = i0.ɵɵngDeclareInjector({ minVersion: "12.0.0", version: "13.1.1", ngImport: i0, type: NgxSkeletonLoaderModule, imports: [[CommonModule]] });
i0.ɵɵngDeclareClassMetadata({ minVersion: "12.0.0", version: "13.1.1", ngImport: i0, type: NgxSkeletonLoaderModule, decorators: [{
            type: NgModule,
            args: [{
                    declarations: [NgxSkeletonLoaderComponent],
                    imports: [CommonModule],
                    exports: [NgxSkeletonLoaderComponent],
                }]
        }] });
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoibmd4LXNrZWxldG9uLWxvYWRlci5tb2R1bGUuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyIuLi8uLi8uLi8uLi9wcm9qZWN0cy9uZ3gtc2tlbGV0b24tbG9hZGVyL3NyYy9saWIvbmd4LXNrZWxldG9uLWxvYWRlci5tb2R1bGUudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUEsT0FBTyxFQUF1QixRQUFRLEVBQUUsTUFBTSxlQUFlLENBQUM7QUFDOUQsT0FBTyxFQUFFLFlBQVksRUFBRSxNQUFNLGlCQUFpQixDQUFDO0FBRS9DLE9BQU8sRUFBRSwwQkFBMEIsRUFBRSxNQUFNLGlDQUFpQyxDQUFDO0FBQzdFLE9BQU8sRUFBMkIsMEJBQTBCLEVBQUUsTUFBTSxvQ0FBb0MsQ0FBQzs7QUFPekcsTUFBTSxPQUFPLHVCQUF1QjtJQUNsQyxNQUFNLENBQUMsT0FBTyxDQUFDLE1BQXlDO1FBQ3RELE9BQU87WUFDTCxRQUFRLEVBQUUsdUJBQXVCO1lBQ2pDLFNBQVMsRUFBRSxDQUFDLEVBQUUsT0FBTyxFQUFFLDBCQUEwQixFQUFFLFFBQVEsRUFBRSxNQUFNLEVBQUUsQ0FBQztTQUN2RSxDQUFDO0lBQ0osQ0FBQzs7MEpBTlUsdUJBQXVCOzJKQUF2Qix1QkFBdUIsaUJBSm5CLDBCQUEwQixhQUMvQixZQUFZLGFBQ1osMEJBQTBCOzJKQUV6Qix1QkFBdUIsWUFIekIsQ0FBQyxZQUFZLENBQUM7MkZBR1osdUJBQXVCO2tCQUxuQyxRQUFRO21CQUFDO29CQUNSLFlBQVksRUFBRSxDQUFDLDBCQUEwQixDQUFDO29CQUMxQyxPQUFPLEVBQUUsQ0FBQyxZQUFZLENBQUM7b0JBQ3ZCLE9BQU8sRUFBRSxDQUFDLDBCQUEwQixDQUFDO2lCQUN0QyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IE1vZHVsZVdpdGhQcm92aWRlcnMsIE5nTW9kdWxlIH0gZnJvbSAnQGFuZ3VsYXIvY29yZSc7XG5pbXBvcnQgeyBDb21tb25Nb2R1bGUgfSBmcm9tICdAYW5ndWxhci9jb21tb24nO1xuXG5pbXBvcnQgeyBOZ3hTa2VsZXRvbkxvYWRlckNvbXBvbmVudCB9IGZyb20gJy4vbmd4LXNrZWxldG9uLWxvYWRlci5jb21wb25lbnQnO1xuaW1wb3J0IHsgTmd4U2tlbGV0b25Mb2FkZXJDb25maWcsIE5HWF9TS0VMRVRPTl9MT0FERVJfQ09ORklHIH0gZnJvbSAnLi9uZ3gtc2tlbGV0b24tbG9hZGVyLWNvbmZpZy50eXBlcyc7XG5cbkBOZ01vZHVsZSh7XG4gIGRlY2xhcmF0aW9uczogW05neFNrZWxldG9uTG9hZGVyQ29tcG9uZW50XSxcbiAgaW1wb3J0czogW0NvbW1vbk1vZHVsZV0sXG4gIGV4cG9ydHM6IFtOZ3hTa2VsZXRvbkxvYWRlckNvbXBvbmVudF0sXG59KVxuZXhwb3J0IGNsYXNzIE5neFNrZWxldG9uTG9hZGVyTW9kdWxlIHtcbiAgc3RhdGljIGZvclJvb3QoY29uZmlnPzogUGFydGlhbDxOZ3hTa2VsZXRvbkxvYWRlckNvbmZpZz4pOiBNb2R1bGVXaXRoUHJvdmlkZXJzPE5neFNrZWxldG9uTG9hZGVyTW9kdWxlPiB7XG4gICAgcmV0dXJuIHtcbiAgICAgIG5nTW9kdWxlOiBOZ3hTa2VsZXRvbkxvYWRlck1vZHVsZSxcbiAgICAgIHByb3ZpZGVyczogW3sgcHJvdmlkZTogTkdYX1NLRUxFVE9OX0xPQURFUl9DT05GSUcsIHVzZVZhbHVlOiBjb25maWcgfV0sXG4gICAgfTtcbiAgfVxufVxuIl19