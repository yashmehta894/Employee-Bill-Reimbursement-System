import {Injectable} from '@angular/core';
import {BaseRequestOptions, Headers} from '@angular/http'


@Injectable()
export class CustomRequestOptions extends BaseRequestOptions {

  private superHeaders: Headers;

  get headers() {

    const token = localStorage.getItem('token');
    // const token = 'abc'
    console.log("called");
    if (token) {
      this.superHeaders.set('token', token);
    } else {
      this.superHeaders.delete('token');
    }
    return this.superHeaders;
  }

  set headers(headers: Headers) {
    this.superHeaders = headers;
  }

  constructor() {
    super();
  }

}

