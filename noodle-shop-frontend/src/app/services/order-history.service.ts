import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OrderHistory } from '../common/order-history';

@Injectable({
  providedIn: 'root'
})
export class OrderHistoryService {

  private orderUrl = environment.noodleShopApiUrl + '/orders';

  constructor(private httpClient: HttpClient) { }

  getOrderHistory(theEmail: string): Observable<GetResponseOrderHistory>{
    // Need to build URL based on the customer email
    const orderHistoryUrl = `${this.orderUrl}/search/findByCustomerEmail?email=${theEmail}&sort=dateCreated,DESC`;

    return this.httpClient.get<GetResponseOrderHistory>(orderHistoryUrl);
  }
}
// Unwraps the JSON from Spring Data REST _embedded entry
interface GetResponseOrderHistory{
  _embedded: {
    orders: OrderHistory[];
  }
}