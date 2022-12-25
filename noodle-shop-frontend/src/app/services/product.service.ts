import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../common/product';
import { ProductCategory } from '../common/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = environment.noodleShopApiUrl + '/products';
  private categoryUrl = environment.noodleShopApiUrl + '/product-category';

  constructor(private httpClient: HttpClient) { }

  // getAllProductList(): Observable<Product[]>{
  //   return this.httpClient.get<GetResponseProducts>(this.baseUrl).pipe(
  //     map(response => response._embedded.products)
  //   );
  // }
  getAllProductListPaginate(thePage: number, thePageSize: number): Observable<GetResponseProducts>{
    const searchUrl = `${this.baseUrl}?`+`&page=${thePage}&size=${thePageSize}`;
    console.log(searchUrl)
    return this.httpClient.get<GetResponseProducts>(searchUrl);
  }

  getProductListPaginate(thePage: number, thePageSize: number,theCategoryId: number ): Observable<GetResponseProducts>{
    // Need to build URL based on category id, page and size
    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`+`&page=${thePage}&size=${thePageSize}`;
    
    return this.httpClient.get<GetResponseProducts>(searchUrl);
  }

  // getProductList(theCategoryId: number ): Observable<Product[]>{
  //   // Need to build URL based on category id
  //   const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`;
    
  //   return this.getProducts(searchUrl);
  // }

  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpClient.get<GetResponseProductCategory>(this.categoryUrl).pipe(
      map(response => response._embedded.productCategory)
    );
  }

  searchProducts(theKeyword: string): Observable<Product[]> {
    // Need to build URL based on keyword
    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${theKeyword}`;
    
    return this.getProducts(searchUrl);
  }

  searchProductPaginate(thePage: number, 
                        thePageSize: number,
                        theKeyword: string ): Observable<GetResponseProducts>{
    // Need to build URL based on keyword, page and size
    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${theKeyword}`+`&page=${thePage}&size=${thePageSize}`;
    
    return this.httpClient.get<GetResponseProducts>(searchUrl);
  }

  getProductDetail(theProductId: number): Observable<Product> {
    // Need to buid URL based on product id:
    //  http://localhost:8080/api/products/1
    const productUrl = `${this.baseUrl}/${theProductId}`;

    return this.httpClient.get<Product>(productUrl);
  }

  private getProducts(searchUrl: string): Observable<Product[]> {
    return this.httpClient.get<GetResponseProducts>(searchUrl).pipe(
      map(response => response._embedded.products)
    );
  }
  
}
// Unwraps the JSON from Spring Data REST _embedded entry
interface GetResponseProducts{
  _embedded: {
    products: Product[];
  },
  page:{
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}

interface GetResponseProductCategory{
  _embedded: {
    productCategory: ProductCategory[];
  }
}