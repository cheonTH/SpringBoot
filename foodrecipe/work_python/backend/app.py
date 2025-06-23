import os
from flask import Flask, request, jsonify
from flask_cors import CORS # CORS 정책 관리를 위해 필요
from dotenv import load_dotenv
import openai

# .env 파일 로드
load_dotenv()

# Flask 앱 초기화
app = Flask(__name__)
# CORS 설정: 프론트엔드 도메인에서 백엔드 API에 접근할 수 있도록 허용
CORS(app) # 개발 단계에서는 모든 접근을 허용하지만, 실제 배포 시에는 특정 도메인으로 제한하는 것이 좋습니다.
# CORS(app, resources={r"/api/*": {"origins": "http://localhost:3000"}}) # 예시: 특정 도메인 허용

# OpenAI API 키 설정
openai.api_key = os.getenv("OPENAI_API_KEY")

@app.route('/')
def home():
    return "백엔드 서버가 실행 중입니다!"

@app.route('/generate-recipe', methods=['POST'])
def generate_recipe():
    """
    사용자가 입력한 재료를 받아 AI로부터 레시피를 생성합니다.
    """
    data = request.get_json()
    ingredients = data.get('ingredients', [])

    if not ingredients:
        return jsonify({"error": "재료를 입력해주세요."}), 400

    # 재료 리스트를 자연어 프롬프트로 변환
    ingredients_str = ", ".join(ingredients)
    prompt = f"다음 재료들만을 사용하여 만들 수 있는 맛있는 요리 레시피를 상세하게 알려주세요. 재료: {ingredients_str}. 필요한 모든 단계와 팁을 포함하여 명확하게 작성해주세요."

    try:
        # OpenAI API 호출
        response = openai.chat.completions.create(
            model="gpt-3.5-turbo",  # 또는 "gpt-4" 등 더 최신 모델 사용 가능
            messages=[
                {"role": "system", "content": "당신은 냉장고 재료만으로 창의적이고 상세한 레시피를 제공하는 요리 전문가입니다."},
                {"role": "user", "content": prompt}
            ],
            max_tokens=500,  # 생성될 레시피의 최대 길이
            temperature=0.7  # 창의성 조절 (0.0은 보수적, 1.0은 창의적)
        )
        
        # AI 응답 파싱
        recipe_content = response.choices[0].message.content.strip()
        return jsonify({"recipe": recipe_content}), 200

    except openai.APIStatusError as e:
        print(f"OpenAI API 오류: {e}")
        return jsonify({"error": f"AI 레시피 생성 중 오류 발생: {e.status_code} - {e.response}"}), e.status_code
    except Exception as e:
        print(f"서버 오류: {e}")
        return jsonify({"error": "서버 내부 오류가 발생했습니다."}), 500

if __name__ == '__main__':
    # 개발 서버 실행 (배포 시에는 Gunicorn, Nginx 등 사용 권장)
    app.run(debug=True, port=5000)