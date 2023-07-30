
# Sosyal Medya Uygulaması Backend

Uzun zamandır aklımda olan bir projeyi okulların bitmesiyle yapmaya karar verdim amacım daha önce kullanmadığım react ve java spring boot ile fullstack bir proje yapmaktı ve böylece web teknolojilerini öğrenmek üzerine hızlı bir giriş yaptım ,aklımdaki çoğu özelliği ekledim ama halen geliştirmeye devam ediyorum son hali yakında.Bu projemin backend kodlarını içeren reposu


## API Kullanımı

#### Kaydolma
```http
  Post /api/users
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `body` | `Map` | **Gerekli**. API anahtarınız. |

#### Giriş Yapma

```http
  Post /api/auth
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `authorization` | `Map` | **Gerekli**. API anahtarınız. |

#### Şifremi Unuttum

```http
  Post /api/forgot
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `body` | `Map` | **Gerekli**. API anahtarınız. |
#### Tüm Kullanıcıları getirme

```http
  Get /api/getAllUsers
```

#### Paylaşım ekleme

```http
  Post /api/createShare/${username}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `username` | `String` | **Gerekli**. API anahtarınız. |




#### Paylaşım Güncelleme

```http
  Put /api/updateShare/${id}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |

#### Paylaşım Silme

```http
  delete /api/deleteShare/${id}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |

#### Herhangi bir kullanıcıya ait paylaşımları getirme

```http
  Get /api/getUserPost/${username}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `username` | `String` | **Gerekli**. API anahtarınız. |

#### Herhangi bir paylaşıma  yorum ekleme ve yorumu atan kullanıcı

```http
  Post /api/addComment/${username}/${id}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `username` | `String` | **Gerekli**. API anahtarınız. |
| `id` | `Long` | **Gerekli**. API anahtarınız. |
#### Herhangi bir paylaşıma gelen Yorumlar

```http
  Get /api/getComments/${id}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |


#### Herhangi bir paylaşıma gelen like sayısı

```http
  Get /api/getLikeCount/${id}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |

#### Herhangi bir paylaşıma like atma

```http
  Put /api/plusLikeCount/${id}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |
#### Herhangi bir paylaşıma like geri alma

```http
  Put /api/minusLikeCount/${id}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Gerekli**. API anahtarınız. |





#### Tüm postları getirir

```http
  GET /api/get shares
```
#### 2 kişi arasındaki mesajları getirme

```http
  Post /api/getMessages/${sender}/${receiver}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `sender` | `String` | **Gerekli**. API anahtarınız. |
| `receiver` | `String` | **Gerekli**. API anahtarınız. |

#### mesaj atma 

```http
  Post /api/addMessage/${sender}/${receiver}
```
| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `sender` | `String` | **Gerekli**. API anahtarınız. |
| `receiver` | `String` | **Gerekli**. API anahtarınız. |
| `body` | `Map` | **Gerekli**. API anahtarınız. |

#### Apilerin kullanımına örnek

[service](https://github.com/furkancan2107/socialmedia-app-frontend/blob/main/src/api/server.jsx) kısmı burada.
#### Frontend Kodları

[frontend](https://github.com/furkancan2107/socialmedia-app-frontend) kısmı burada.





  
