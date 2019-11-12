import pandas as pd
from sklearn.model_selection import train_test_split
import konlpy
from keras.preprocessing.text import Tokenizer
from keras.preprocessing.sequence import pad_sequences
from keras.models import Sequential
from keras.layers import Embedding, SimpleRNN , LSTM , GRU, Dense , Dropout

data = pd.read_csv('C:/Users/GIGABYTE/full_review.csv', encoding='CP949' )
data = data.loc[:, ~data.columns.str.contains('^Unnamed')] 
df = data

X=df[['no','review']]
Y=df['label']

X_train, X_test, Y_train, Y_test = train_test_split(X, Y, random_state=0)

from konlpy.tag import Okt
import warnings
warnings.filterwarnings('ignore')

okt = Okt()

X_train['review'] = X_train['review'].str.replace("[^ㄱ-ㅎㅏ-ㅣ가-힣 ]" , "")  #ㄱ-ㅎ ㅏ-ㅣ 가-힣 (빈공간) ^=>~이 아니면 ""로
X_test['review'] = X_test['review'].str.replace("[^ㄱ-ㅎㅏ-ㅣ가-힣 ]" , "")

# 처리된 데이터 저장
X_train_data = []

stop_words = ['은' , '는' , '이']

for sentence in X_train['review']:
    temp_X = []
    temp_X = okt.morphs(sentence, stem=True, )  # morphs 는 형태소 분석 #stem : 유사한 단어 통합(만들고 만들어 만들 만듬)
    # 분리된 단어들에서 불용어를 제거
    temp_X = [word for word in temp_X if not word in stop_words]
    X_train_data.append(temp_X)

X_test_data = []

for sentence in X_test['review']:
    temp_X = []
    temp_X = okt.morphs(sentence, stem=True, )  # morphs 는 형태소 분석 #stem : 유사한 단어 통합(만들고 만들어 만들 만듬)
    # 분리된 단어들에서 불용어를 제거
    temp_X = [word for word in temp_X if not word in stop_words]
    X_test_data.append(temp_X)


#최대 단어수 (사전의 크기)
max_words = 35000
#입력 문자수
maxlen = 30

tokenizer = Tokenizer(num_words=max_words)
#최대 단어수만큼 잘라낸다. 즉 35000등 이상인 단어만 선택한다.

tokenizer.fit_on_texts(X_train_data)

#문자를 정수로 변환
X_train_data = tokenizer.texts_to_sequences(X_train_data)
X_test_data = tokenizer.texts_to_sequences(X_test_data)


X_train = pad_sequences(X_train_data, maxlen=maxlen)
X_test = pad_sequences(X_test_data, maxlen=maxlen)

#LSTM 과 GRU는 이전 내용을 기억하기 위함

model2 = Sequential()
model2.add(Embedding(max_words , 30 ))
model2.add(Dropout(0.5))
model2.add(SimpleRNN(128))
model2.add(Dropout(0.5))
model2.add(Dense(1, activation='sigmoid'))

model2.compile(loss='binary_crossentropy' , optimizer='adam' , metrics=['acc'])

model2.fit(X_train , Y_train , epochs=10 , batch_size=50 , validation_data=(X_test , Y_test))

testing = '배달도 빠르고 기사님이 친절해요'


pred = []

temp_X = okt.morphs(testing , stem=True ,)  # morphs 는 형태소 분석 #stem : 유사한 단어 통합(만들고 만들어 만들 만듬)
    #분리된 단어들에서 불용어를 제거
temp_X = [word for word in temp_X if not word in stop_words]
pred.append(temp_X)

pred = tokenizer.texts_to_sequences(pred)
pred = pad_sequences(pred, maxlen=30)

y = model2.predict(pred)[0][0]

if y < 0.5 :
    print('부정')
else :
    print('긍정')

