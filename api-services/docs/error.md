
## Hibernate could not initialize proxy – no Session

---
https://www.baeldung.com/hibernate-initialize-proxy-exception

### Understanding the Error

Access to a lazy-loaded object outside of the context of an open Hibernate session will result in this exception.

It's important to understand what is Session, Lazy Initialisation, and Proxy Object and how they come together in the Hibernate framework.

 - Session is a persistence context that represents a conversation between an application and the database
 - Lazy Loading means that the object will not be loaded to the Session context until it is accessed in code.
 - Hibernate creates a dynamic Proxy Object subclass that will hit the database only when we first use the object.

This error means that we try to fetch a lazy-loaded object from the database by using a proxy object, but the Hibernate session is already closed.

### How to Avoid the Error

 - Open Session in Upper Layer
 - Turning on enable_lazy_load_no_trans Property
 - Using  FetchType.EAGER Strategy
 - Using Join Fetching